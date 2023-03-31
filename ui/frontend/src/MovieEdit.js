import React, {Component} from 'react';
import {Link, withRouter} from 'react-router-dom';
import Form from 'react-validation/build/form';

import {Button, Container, FormGroup} from 'reactstrap';
import AppNavbar from './AppNavbar';
import {
    Box,
    Chip,
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
    };

    constructor(props) {
        super(props);
        this.state = {
            item: this.emptyItem,
            genres: []
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
            .then(data => this.state.genres = data);
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

    handleChangeGenres = (event) => {
        const {
            target: {value},
        } = event;
        /* setPersonName(
             // On autofill we get a stringified value.
             typeof value === 'string' ? value.split(',') : value,
         );*/
    };


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
        const {genres} = this.state.genres;
        const title = <h2>{item.id ? 'Edit Movie' : 'Add Movie'}</h2>;
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
        return <div>
            <AppNavbar/>
            <Box sx={{'& .MuiTextField-root': {m: 1, width: '25ch'},}} noValidate autoComplete="off">
                <Container>
                    {title}
                    <Form onSubmit={this.handleSubmit}>
                        <FormGroup>
                            <TextField label="Name" variant="outlined" name="name" id="name" value={item.name || ''}
                                       className="w-50"
                                       onChange={this.handleChange} autoComplete="name" required/>
                        </FormGroup>
                        <FormControl sx={{m: 1, width: 300}}>
                            <InputLabel id="demo-multiple-chip-label">Genres</InputLabel>
                            <Select
                                labelId="demo-multiple-chip-label"
                                id="demo-multiple-chip"
                                multiple
                                value={item.genres}
                                onChange={this.handleChangeGenres}
                                input={<OutlinedInput id="select-multiple-chip" label="Chip"/>}
                                renderValue={(selected) => (
                                    <Box sx={{display: 'flex', flexWrap: 'wrap', gap: 0.5}}>
                                        {selected.map((value) => (
                                            <Chip key={value.id} label={value.name}/>
                                        ))}
                                    </Box>
                                )}>
                                {this.state.genres.map((name) => (
                                    <MenuItem key={name} value={name}>
                                        {name}
                                    </MenuItem>
                                ))}
                            </Select>
                        </FormControl>
                        <FormGroup>
                            <TextField label="Year" type="number" variant="outlined" name="year" id="year"
                                       value={item.year || ''}
                                       onChange={this.handleChange} autoComplete="year" required/>
                        </FormGroup>
                        <FormGroup>
                            <FormControl sx={{m: 1, minWidth: 80}}>
                                <InputLabel id="ageLimit">Age</InputLabel>
                                <Select
                                    labelId="ageLimit"
                                    id="ageLimit"
                                    value={item.ageLimit}
                                    label="Age Limit"
                                    onChange={this.handleChangeAgeLimit}
                                    required>
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
                        <FormControl sx={{m: 1}}>
                            <FormGroup>
                                <Typography component="legend">Rating</Typography>
                                <Rating name="rating" value={item.rating || ''} onChange={this.handleChange}
                                        precision={1}/>
                            </FormGroup>
                        </FormControl>
                        <FormGroup>
                            <TextField label="Synopsis" variant="outlined" name="synopsis" id="synopsis"
                                       className="w-50"
                                       value={item.synopsis || ''}
                                       onChange={this.handleChange} autoComplete="synopsis" multiline
                                       rows={3}/>
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