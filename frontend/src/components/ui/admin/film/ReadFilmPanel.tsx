import {Film, PaginatedFilms} from "../../../../types/api.ts";
import {useEffect, useState} from "react";


const ReadFilmPanel = () =>
{
    const [films, setFilms] = useState<Film[]>([])

    const fetchFilms = () => {
        fetch(import.meta.env.VITE_SAKILA_API+`/film/list?page=0`)
            .then(res => res.json())
            .then((d: PaginatedFilms) => {
                setFilms([...films].concat(d.films));
            });
    }

    useEffect(() => {
        fetchFilms();
    }, [])

    const filmRows = films.map(f => <tr key={f.id}><td>{f.id}</td><td>{f.title}</td><td>{f.description}</td><td>{f.price}</td></tr> )

    return (
        <>
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Price</th>
                </tr>
                </thead>
                <tbody>
                {filmRows}
                </tbody>
            </table>
        </>
    )
}

export default ReadFilmPanel