import {PaginatedFilms} from "../../../../types/api.ts";
import {useEffect, useState} from "react";


const ReadFilmPanel = () =>
{
    const [paginatedFilms, setPaginatedFilms] = useState<PaginatedFilms>()

    const fetchFilms = (page: number) => {
        fetch(import.meta.env.VITE_SAKILA_API+`/film/list?page=${page}`)
            .then(res => res.json())
            .then((d: PaginatedFilms) => {
                setPaginatedFilms(d);
            });
    }

    useEffect(() => {
        fetchFilms(0);
    }, [])

    const filmRows = paginatedFilms === undefined ? null :  paginatedFilms.films.map(f => <tr key={f.id}><td>{f.id}</td><td>{f.title}</td><td>{f.description}</td><td>£ {f.price}</td></tr> )

    return (
        <div>
            <button onClick={() => fetchFilms((paginatedFilms?.currentPage ?? 0)-1)} disabled={(paginatedFilms?.currentPage ?? 0) < 1}>Previous</button>
            <button onClick={() => fetchFilms((paginatedFilms?.currentPage ?? 0)+1)} disabled={(paginatedFilms?.currentPage ?? 0) >= (paginatedFilms?.totalPages ?? 1)-1}>Next</button>
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
        </div>
    )
}

export default ReadFilmPanel