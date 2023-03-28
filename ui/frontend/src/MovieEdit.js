import React, {Component} from 'react';
import {Link, withRouter} from 'react-router-dom';
import Form from 'react-validation/build/form';

import {Container, FormGroup} from 'reactstrap';
import AppNavbar from './AppNavbar';
import {Box, FormControl, InputLabel, MenuItem, Rating, Select, TextField, Typography} from "@mui/material";
import Button from '@mui/material/Button';

class MovieEdit extends Component {

    emptyItem = {
        name: '',
        year: 2023,
        ageLimit: 12,
        rating: 5,
        synopsis: '',
    };

    constructor(props) {
        super(props);
        this.state = {
            item: this.emptyItem
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        if (this.props.match.params.id !== 'new') {
            const movie = await (await fetch(`/movies/${this.props.match.params.id}`)).json();
            this.setState({item: movie});
        }
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...this.state.item};
        item[name] = value;
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
        const {item} = this.state;
        const title = <h2>{item.id ? 'Edit Movie' : 'Add Movie'}</h2>;

        return <div>
            <AppNavbar/>
            <Box
                component="form"
                sx={{
                    '& .MuiTextField-root': {m: 1, width: '25ch'},
                }}
                noValidate
                autoComplete="off"
            >
                <Container>
                    {title}
                    <Form onSubmit={this.handleSubmit}>
                        <FormGroup>
                            <TextField label="Name" variant="outlined" name="name" id="name" value={item.name || ''}
                                       onChange={this.handleChange} autoComplete="name" required/>
                        </FormGroup>
                        <FormGroup>
                            <TextField label="Year" variant="outlined" name="year" id="year" value={item.year || ''}
                                       onChange={this.handleChange} autoComplete="year" required/>
                        </FormGroup>
                        <FormGroup>
                            {/* <TextField label="Age Limit" variant="outlined" name="ageLimit" id="ageLimit"
                                       value={item.ageLimit || ''}
                                       onChange={this.handleChange} autoComplete="ageLimit" required/>*/}
                            <FormControl sx={{ m: 1, minWidth: 80 }}>
                            <InputLabel id="ageLimit">Age</InputLabel>
                            <Select
                                labelId="ageLimit-label"
                                id="ageLimit-select"
                                value={item.ageLimit}
                                label="Age Limit"
                                onChange={this.handleChange}                            >
                                <MenuItem value={0}>0</MenuItem>
                                <MenuItem value={1}>1</MenuItem>
                                <MenuItem value={2}>2</MenuItem>
                                <MenuItem value={3}>3</MenuItem>
                                <MenuItem value={4}>4</MenuItem>
                                <MenuItem value={5}>5</MenuItem>
                                <MenuItem value={6}>6</MenuItem>
                                <MenuItem value={7}>7</MenuItem>
                                <MenuItem value={8}>8</MenuItem>
                                <MenuItem value={9}>9</MenuItem>
                                <MenuItem value={10}>10</MenuItem>
                                <MenuItem value={11}>11</MenuItem>
                                <MenuItem value={12}>12</MenuItem>
                                <MenuItem value={13}>13</MenuItem>
                                <MenuItem value={14}>14</MenuItem>
                                <MenuItem value={15}>15</MenuItem>
                                <MenuItem value={16}>16</MenuItem>
                                <MenuItem value={17}>17</MenuItem>
                                <MenuItem value={18}>18</MenuItem>
                            </Select>
                            </FormControl>
                        </FormGroup>
                        <FormControl sx={{ m: 1 }}>
                        <FormGroup >

                            <Typography component="legend">Rating</Typography>
                            <Rating
                                name="rating"
                                value={item.rating || ''}
                                onChange={this.handleChange}
                            />
                        </FormGroup>
                        </FormControl>
                        <FormGroup>
                            <TextField label="Synopsis" variant="outlined" name="synopsis" id="synopsis"
                                       value={item.synopsis || ''}
                                       onChange={this.handleChange} autoComplete="synopsis" multiline
                                       rows={3}/>
                        </FormGroup>
                        <FormGroup className='mt-1'>
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