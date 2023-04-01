import React, {Component} from 'react';
import {Link, withRouter} from 'react-router-dom';
import Form from 'react-validation/build/form';

import {Button, Container, FormGroup} from 'reactstrap';
import AppNavbar from './AppNavbar';
import {
    Box,
    FormControl,
    InputLabel,
    MenuItem,
    OutlinedInput,
    Rating,
    Select,
    TextField,
    Typography
} from "@mui/material";

class MovieEdit extends Component {

    emptyItem = {
        name: '',
        year: 2023,
        ageLimit: 12,
        rating: 5,
        synopsis: '',
        genreIds: [],
        actorIds: [],
        directorId: ''
    };

    constructor(props) {
        super(props);
        this.state = {
            item: this.emptyItem,
            genres: [],
            actors: [],
            directors: []
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        if (this.props.match.params.id !== 'new') {
            const movie = await (await fetch(`/movies/${this.props.match.params.id}`)).json();
            this.setState({item: movie});
        }

        fetch('/genres')
            .then(response => response.json())
            .then(data => {
                this.setState({
                    genres: data
                })
            })
        fetch('/actors')
            .then(response => response.json())
            .then(data => {
                this.setState({
                    actors: data
                })
            })
        fetch('/directors')
            .then(response => response.json())
            .then(data => {
                this.setState({
                    directors: data
                })
            })
    }

    handleChange(event) {
        const target = event.target;
        const name = target.name;
        let value = target.value;
        let item = {...this.state.item};

        if (name === "rating") {
            value = Number(value);
        }

        item[name] = value;
        this.setState({item});
    }

    handleChangeAgeLimit = (event) => {
        let item = {...this.state.item};
        item["ageLimit"] = event.target.value;
        this.setState({item});
    }

    handleChangeGenres(event) {
        let item = {...this.state.item};
        item["genreIds"] = event.target.value;
        this.setState({item});
    }

    handleChangeActors(event) {
        let item = {...this.state.item};
        item["actorIds"] = event.target.value;
        this.setState({item});
    }

    handleChangeDirector(event) {
        let item = {...this.state.item};
        item["directorId"] = event.target.value;
        this.setState({item});
    }

    async handleSubmit(event) {
        event.preventDefault();
        const {item} = this.state;

        await fetch('/movies' + (item.id ? '/' + item.id : ''), {
            method: (item.id) ? 'PUT' : 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item),
        });
        this.props.history.push('/movies');
    }

    render() {
        const {item: {ageLimit, id, name, rating, synopsis, year, genreIds, actorIds, directorId}} = this.state;
        const title = <h2>{id ? 'Edit Movie' : 'Add Movie'}</h2>;

        const ITEM_HEIGHT = 48;
        const ITEM_PADDING_TOP = 8;
        const MenuProps = {
            PaperProps: {
                style: {
                    maxHeight: ITEM_HEIGHT * 4.5 + ITEM_PADDING_TOP,
                    width: 250,
                },
            },
        };
        let genreOptions = this.state.genres.map(function (genre) {
            return {id: genre.id, name: genre.name};
        })
        let actorOptions = this.state.actors.map(function (actor) {
            return {id: actor.id, name: actor.firstname + ' ' + actor.lastname};
        })

        let directorOptions = this.state.directors.map(function (director) {
            return {id: director.id, name: director.firstname + ' ' + director.lastname};
        })

        function getStylesForOptions(id, ids) {
            return {
                fontWeight:
                    ids != null && ids.indexOf(id) === -1
                        ? 100
                        : 600
            };
        }

        return <div>
            <AppNavbar/>
            <Box sx={{'& .MuiTextField-root': {m: 1, width: '25ch'},}} noValidate autoComplete="off">
                <Container>
                    {title}
                    <Form onSubmit={this.handleSubmit}>
                        <FormGroup sx={{m: 1, minWidth: 80}}>
                            <TextField label="Name" variant="outlined" name="name" id="name" value={name || ''}
                                       className="w-50"
                                       onChange={this.handleChange} autoComplete="name" required/>
                        </FormGroup>
                        <FormGroup sx={{m: 1, width: 300}}>
                            <FormControl sx={{m: 1, minWidth: 80}}>
                                <InputLabel>Genres</InputLabel>
                                <div>
                                    {<Select
                                        multiple={true}
                                        variant="outlined"
                                        onChange={this.handleChangeGenres.bind(this)}
                                        input={<OutlinedInput label="Genres"/>}
                                        MenuProps={MenuProps}
                                        value={genreIds != null ? genreIds : []}
                                        required
                                    >
                                        {genreOptions.map((g) => (
                                            <MenuItem
                                                key={g.id}
                                                value={g.id}
                                                style={getStylesForOptions(g.id, genreIds)}
                                            >
                                                {g.name}
                                            </MenuItem>
                                        ))}
                                    </Select>}
                                </div>
                            </FormControl>
                        </FormGroup>
                        <FormGroup sx={{m: 1, minWidth: 80}}>
                            <FormControl>
                                <TextField label="Year" type="number" variant="outlined" name="year" id="year"
                                           value={year || ''}
                                           onChange={this.handleChange} autoComplete="year" required/>
                            </FormControl>
                        </FormGroup>
                        <FormGroup sx={{m: 1, minWidth: 80}}>
                            <FormControl sx={{m: 1, minWidth: 80}}>
                                <InputLabel id="ageLimit">Age</InputLabel>
                                <Select
                                    labelId="ageLimit"
                                    id="ageLimit"
                                    value={ageLimit}
                                    label="Age Limit"
                                    onChange={this.handleChangeAgeLimit}
                                    required>
                                    {(() => {
                                        let td = [];
                                        for (let i = 1; i <= 21; i++) {
                                            td.push(<MenuItem key={i} value={i}>{i}</MenuItem>);
                                        }
                                        return td;
                                    })()}
                                </Select>
                            </FormControl>

                        </FormGroup>
                        <FormGroup sx={{m: 1}}>
                            <FormControl sx={{m: 1, width: 300}}>
                                <Typography component="legend">Rating</Typography>
                                <Rating name="rating" value={rating || ''} onChange={this.handleChange}
                                        precision={1}/>
                            </FormControl>
                        </FormGroup>
                        <FormGroup sx={{m: 1, width: 300}}>
                            <FormControl sx={{m: 1, minWidth: 80}}>
                                <InputLabel>Director</InputLabel>
                                <div>
                                    {<Select
                                        variant="outlined"
                                        onChange={this.handleChangeDirector.bind(this)}
                                        input={<OutlinedInput label="Director"/>}
                                        MenuProps={MenuProps}
                                        value={directorId}
                                        required
                                    >
                                        {directorOptions.map((g) => (
                                            <MenuItem
                                                key={g.id}
                                                value={g.id}
                                            >
                                                {g.name}
                                            </MenuItem>
                                        ))}
                                    </Select>}
                                </div>
                            </FormControl>
                        </FormGroup>
                        <FormGroup sx={{m: 1, width: 300}}>
                            <FormControl sx={{m: 1, minWidth: 80}}>
                                <InputLabel>Actors</InputLabel>
                                <div>
                                    {<Select
                                        multiple={true}
                                        variant="outlined"
                                        onChange={this.handleChangeActors.bind(this)}
                                        input={<OutlinedInput label="Actors"/>}
                                        MenuProps={MenuProps}
                                        value={actorIds != null ? actorIds : []}
                                    >
                                        {actorOptions.map((g) => (
                                            <MenuItem
                                                key={g.id}
                                                value={g.id}
                                                style={getStylesForOptions(g.id, actorIds)}
                                            >
                                                {g.name}
                                            </MenuItem>
                                        ))}
                                    </Select>}
                                </div>
                            </FormControl>
                        </FormGroup>
                        <FormGroup>
                            <TextField label="Synopsis" variant="outlined" name="synopsis" id="synopsis"
                                       className="w-50"
                                       value={synopsis || ''}
                                       onChange={this.handleChange} autoComplete="synopsis" multiline
                                       rows={5}/>
                        </FormGroup>
                        <FormGroup sx={{m: 1}}>
                            <Button variant="contained" color="primary" type="submit">Save</Button>{' '}
                            <Button variant="contained" color="secondary" tag={Link} to="/movies">Cancel</Button>
                        </FormGroup>
                    </Form>
                </Container>
            </Box>
        </div>
    }
}

export default withRouter(MovieEdit);