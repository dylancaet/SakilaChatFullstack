import {PaginatedFilms} from "../../../../types/api.ts";
import {useEffect, useState} from "react";
import "./AdminPanel.css"

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
        <div className="admin-panel-background admin-panel-table">
            <button onClick={() => fetchFilms((paginatedFilms?.currentPage ?? 0)-1)} disabled={(paginatedFilms?.currentPage ?? 0) < 1}>Previous</button>
            <button onClick={() => fetchFilms((paginatedFilms?.currentPage ?? 0)+1)} disabled={(paginatedFilms?.currentPage ?? 0) >= (paginatedFilms?.totalPages ?? 1)-1}>Next</button>
            <button onClick={() => fetchFilms(paginatedFilms?.currentPage ?? 0)}>Refresh</button>
            <table>
                <colgroup>
                    <col width="5%" />
                    <col width="15%" />
                    <col width="75%" />
                    <col width="5%" />
                </colgroup>
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