import {Film} from "../../../types/api.ts";
import "./FilmCard.scss"
import {useState} from "react";
import FilmCardInfo from "./FilmCardInfo.tsx";

interface FilmProps {
    film: Film
    clickable: boolean;
}

function random(seed: number) {
    const x = Math.sin(seed) * 10000;
    return x - Math.floor(x);
}

const FilmCard = (props: FilmProps) =>
{
    const [infoVisible, setInfoVisible] = useState(false);
    const postsers = ["./posters/poster_1.jpg", "./posters/poster_2.jpg", "./posters/poster_3.webp", "./posters/poster_4.png"]
    const poster = postsers[Math.floor(random(props.film.id) * postsers.length)];

    const filmCardInfo = infoVisible && props.clickable ? <FilmCardInfo film={props.film} image={poster} /> : null;

    return (
        <>
            {filmCardInfo}
            <div className="film-card" onClick={() => setInfoVisible(!infoVisible)}>
                <div>
                    <img src={poster}  alt={props.film.title + " poster"}/>
                </div>
                <div className="film-card-info">
                    <h5>{props.film.title}</h5>
                    <p><i>£{props.film.price}</i></p>
                </div>
            </div>
        </>
    )
}

export default FilmCard;