import axios from 'axios'
import {useEffect, useState} from 'react'
import * as ReactBootStrap from 'react-bootstrap'


const MovieTable = ({movieItems}) => {

    const [allMovies, setAllMovies] = useState({allMovies: []})
    const [items, setItems] =  useState({movies: []})
    const [users, setUsers] =  useState({userlist: []})
    const [loggedUser, setloggedUser] = useState({loggedUser: []})
    const [friend, setFriend] = useState({friend: []})
    const [newMovieItem, setNewMovieItem] = useState({
        title: "",
        director: "",
        yearReleased: "",
        synopsis: ""
    })
    const [selectedMovie, setSelectedMovie] = useState({
        title: "",
        director: "",
        yearReleased: "",
        synopsis: ""
    })

    // useEffect(()=> {
    //     const fetchAllMovies = async () => {
    //         const {data} = await axios("http://localhost:8080/movie/allmovies")
    //         setItems({movies: data})
    //         console.log(data)
    //     }
    //     fetchAllMovies()
    // }, [setItems])


    //hardcoded logged in user
    useEffect(() => {
        const fetchLoggedUser = async () => {
            const {data} = await axios("http://localhost:8080/user/1")
            setloggedUser({loggedUser: data})
            console.log(data)
        }
        fetchLoggedUser()
    }, [setloggedUser])

    function getAllMovies(){
        const fetchAllMovies = async () => {
            const {data} = await axios("http://localhost:8080/movie/allmovies")
            setItems({movies: data})
            console.log(data)
        }
        fetchAllMovies()
    }

    useEffect(() => {
        const fetchAllMoviesForList = async () => {
            const {data} = await axios("http://localhost:8080/movie/allmovies")
            setAllMovies({allMovies: data})
            console.log(data)
        }
        fetchAllMoviesForList()
    }, [setAllMovies])

    function changeSelectedMovie(e){
        const setMov = async () => {
            const{data} = await axios("http://localhost:8080/movie/" + e.target.value)
            setSelectedMovie({selectedMovieItem: data})
            console.log(data)
        }
        setMov()
    }


    function displayMyMovies(){
        const fetchMyMovies = async () => {
            const {data} = await axios("http://localhost:8080/user/" + loggedUser.loggedUser.userId)
            console.log(loggedUser.loggedUser)
            setItems({movies: data.movieList})
            console.log(data)
        }
        fetchMyMovies()
    }

    useEffect(()=> {
        const fetchAllUsers = async () => {
            const {data} = await axios("http://localhost:8080/user/allusers")
            setUsers({userlist: data})
            console.log(data)
        }
        fetchAllUsers()
    }, [setUsers])

    function setSelectedFriend(e){
        const setFr = async () => {
            const{data} = await axios("http://localhost:8080/user/" + e.target.value)
            setFriend({friend: data})
            console.log(data)
        }
        setFr()
    }

    function addNewMovie(e){
        const newMovie = {...newMovieItem}
        newMovie[e.target.id] = e.target.value
        setNewMovieItem(newMovie)
        console.log(newMovie)
    }
    function addNewMovieSubmit(e){
        console.log(newMovieItem.title)
        axios.post("http://localhost:8080/movie/addmovie", {
            title: newMovieItem.title,
            director: newMovieItem.director,
            yearReleased: newMovieItem.yearReleased,
            synopsis: newMovieItem.synopsis
        })
            .then(response => {console.log(response.data)})
    }
    // function addMovieToList(e){
    //     const selectedMovie = {...selectedMovie}
    //     selectedMovie[e.target.id] = e.target.value
    //     setSelectedMovie(selectedMovie)
    //     console.log(selectedMovie)
    // }
    function addMovieToListSubmit(e){
        const selectedMovie = {...selectedMovie}
        selectedMovie[e.target.id] = e.target.value
        setSelectedMovie(selectedMovie)
        console.log(selectedMovie.title)
        axios.put("http://localhost:8080/movie/addmovie/" + loggedUser.loggedUser.userId, {
            title: selectedMovie.title,
            director: selectedMovie.director,
            yearReleased: selectedMovie.yearReleased,
            synopsis: selectedMovie.synopsis
        })
            .then(response => {console.log(response.data)})
    }

    return (
        <>
        <div className="parent">
            <h2>Watchlist</h2>
            <div className="grid-child-element green">
                <label htmlFor="friends">Friendlist:</label>

                <select name="friends" id="friends" onChange={setSelectedFriend}>
                    {users.userlist.map((usr) =>
                        <option key={usr.userId} value={usr.userId}>{usr.firstName + " " + usr.lastName}</option>
                    )}
                </select>
            </div>
            <div className='parent'>
                <div className='child'>
                    <button type="button" onClick={getAllMovies}>Display all Movies</button>
                </div>
                <div className='child'>
                    <button type="button">Display movies in common</button>
                </div>
                <div className='child'>
                    <button type="button" onClick={displayMyMovies}>Display only my movies</button>
                </div>
            </div>
           <ReactBootStrap.Table bordered hover>
               <thead>
                <tr>
                   <th>Title</th>
                   <th>Director</th>
                   <th>Year Released</th>
                   <th>Synopsis</th>
                </tr>
               </thead>
               <tbody>
               {
                   items.movies && items.movies.map((item) => (
                       <tr key={item.movieId}>
                           <td>{item.title}</td>
                           <td>{item.director}</td>
                           <td>{item.yearReleased}</td>
                           <td>{item.synopsis}</td>
                       </tr>
                   ))
               }
               </tbody>
           </ReactBootStrap.Table>
        </div>
            <div className="parent">
                <form className="form-container">
                    <label htmlFor="selectmovie">Select a movie from the database:</label>

                    <select name="movieList" id="movieList" onChange={changeSelectedMovie} onSubmit={addMovieToListSubmit}>
                        {allMovies.allMovies.map((movie) =>
                            <option key={movie.movieId} value={movie.movieId}>{movie.title + " " + "(" + movie.yearReleased + ") by " + movie.director}</option>
                        )}
                    </select>
                    <input type="submit" value="Add to list"/>
                </form>
            </div>

    <div className="parent" id="newMovieForm">
        <label className="addmovie">Add a new movie to the database:</label>
        <form className="form-container" onSubmit={addNewMovieSubmit}>

                <label>Title: </label>
                <input type="text" id= "title" placeholder="Title" value={newMovieItem.title} onChange={addNewMovie} required/>

                <label>Director: </label>
                <input type="text" id= "director" placeholder="Director" value={newMovieItem.director} onChange={addNewMovie} required/>


                <label>Year Released: </label>
                <input type="text" id= "yearReleased" placeholder="Year Released" value={newMovieItem.yearReleased} onChange={addNewMovie} required/>


                <label>Synopsis: </label>
                <input type="text" id= "synopsis" placeholder="Synopsis" value={newMovieItem.synopsis} onChange={addNewMovie} required/>

            <input type="submit" value="Add to database"/>
        </form>
    </div>
    </>
    )
}

export default MovieTable