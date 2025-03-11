import {Film, PaginatedFilms} from "../../../types/api.ts";
import FilmCard from "./FilmCard.tsx";
import "./FilmCollection.css";
import {useEffect, useState} from "react";

// interface FilmCollectionProps
// {
//     films: Film[];
// }

interface PaginatedResult
{
    page: number;
    totalPages: number;
}

const FilmCollection = () =>
{
    const [films, setFilms] = useState<Film[]>([]);
    const [userFilter, setUserFilter] = useState("");
    const [page, setPage] = useState<number>(0);
    const [paginatedItems, setPaginatedItems] = useState<PaginatedResult>();

    const fetchFilms = () => {
        fetch(import.meta.env.VITE_SAKILA_API+`/film/list?page=${page}`)
            .then(res => res.json())
            .then((d: PaginatedFilms) => {
                setFilms([...films].concat(d.films));
                setPage(page+1);
                setPaginatedItems({page: d.currentPage, totalPages: d.totalPages});
            });
    }

    useEffect(() => {
        fetchFilms();
    }, [])

    /* FIX: BETTER FETCHING & SETTING OF FILMS */
    const handleUserSearch = (e: React.FormEvent<HTMLFormElement>) =>
    {
        e.preventDefault();

        if (handleUserSearch.length < 1) {
            return;
        }

        fetch(`http://localhost:8080/api/sakila/film/list?0&title=${userFilter}`)
            .then(res => res.json())
            .then((d: PaginatedFilms) => {
                setFilms(d.films);
                setPage(page+1)
                setPaginatedItems({page: d.currentPage, totalPages: d.totalPages});
            });
    }

    let loadButton = null;

    if (paginatedItems?.totalPages) {
        loadButton = paginatedItems?.page !== paginatedItems?.totalPages ? (
            <button className="load-button" onClick={fetchFilms}>
                load more
            </button>
        ) : ''
    }


    const filmCards = films.map(f => <FilmCard key={f.id} film={f} clickable={true}/>)


    return (
        <>
            <title>Sakila Film Chat</title>
            <div className="film-search-container">
                <form className="film-search-container" onSubmit={(e) => handleUserSearch(e)}>
                    <label>search</label>
                    <input type="text" value={userFilter} onChange={e => setUserFilter(e.target.value)}/>
                </form>
            </div>
            <div>
                <div className="film-collection">
                    {filmCards}
                </div>
                <div className="load-container">
                    {loadButton}
                </div>
            </div>
        </>
    )
}

export default FilmCollection