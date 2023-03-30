import React, {Component} from 'react';
import {Button, ButtonGroup, Container, FormGroup, Table} from 'reactstrap';
import AppNavbar from './AppNavbar';
import {Link} from 'react-router-dom';
import {Rating, TextField} from "@mui/material";

class MovieList extends Component {

    constructor(props) {
        super(props);
        this.state = {movies: []};
        this.remove = this.remove.bind(this);
        this.searchMovies = this.searchMovies.bind(this);
    }

    componentDidMount() {
        fetch('/movies')
            .then(response => response.json())
            .then(data => this.setState({movies: data}));
    }

    async remove(id) {
        await fetch(`/movies/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedMovies = [...this.state.movies].filter(i => i.id !== id);
            this.setState({movies: updatedMovies});
        });
    }

    async searchMovies(value) {
        const requestOptions = {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'text/plain'
            },
            body: value
        };

        await fetch('/movies/find', requestOptions)
            .then(response => response.json())
            .then(data => this.setState({movies: data}));
    }

    render() {
        const {movies: movies} = this.state;

        const movieList = movies.map(movie => {
            return <tr key={movie.id}>
                <td style={{whiteSpace: 'nowrap'}}>{movie.name}</td>
                <td>{movie.year}</td>
                <td>{movie.ageLimit}</td>
                <td>
                    <Rating name="rating" value={movie.rating} readOnly/>
                </td>
                <td>
                    <div  style={{display: 'flex', justifyContent: 'flex-end'}}>
                        <ButtonGroup>

                            <Button size="sm" className="mr-2"  color="primary" tag={Link} to={"/movies/" + movie.id} >Edit</Button>


                            <Button size="sm" className="mr-2"  color="danger" onClick={() => this.remove(movie.id)} >Delete</Button>

                        </ButtonGroup>
                    </div>
                </td>
            </tr>
        });

        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <div className="container-fluid d-flex justify-content-end">
                        <Button className="mt-1"  color="success" tag={Link} to="/movies/new">Add Movie</Button>
                    </div>
                    <h3>Movies</h3>
                    <TextField id="search-movies" label="Search movie" type="search" variant="filled" onChange={(e) => {
                        return this.searchMovies(e.target.value);
                    }} fullWidth/>
                    <Table className="mt-2">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Year</th>
                            <th>Age Limit</th>
                            <th>Rating</th>
                            <th>
                                <div style={{display: 'flex', justifyContent: 'flex-end'}}>Actions</div>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        {movieList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default MovieList;