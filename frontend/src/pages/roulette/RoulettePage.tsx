import ChatBox from "../../components/ui/chatbox/ChatBox.tsx";
import "./RoulettePage.css"
import Spinner from "../../components/ui/roulette/spinner/Spinner.tsx";
import {useEffect, useState} from "react";
import {Film, PaginatedFilms} from "../../types/api.ts";

const RoulettePage = () =>
{

    const [films, setFilms] = useState<Film[]>([]);

    useEffect(() =>
    {
        fetch(`http://localhost:8080/api/sakila/film/list?page=0`)
            .then(res => res.json())
            .then((d: PaginatedFilms) => {setFilms([...films].concat(d.films))});
    }, []);

    return (
        <>
            <div className="roulette-grid-container">

                <div className="roulette-chatbox-container">
                    <ChatBox/>
                </div>

                <div className="roulette-spinner-container">
                    <Spinner actualFilm={films[0]} fillerFilms={films} />
                </div>

                <div className="roulette-entries-container">
                </div>

                <div className="roulette-input-container">
                </div>

                <div className="roulette-history-container">
                </div>

            </div>
        </>
    )
}

export default RoulettePage;