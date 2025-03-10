import {Film} from "../../../types/api.ts";
import "./FilmCardInfo.css";

interface FilmCardInfoProps
{
    film: Film;
    image: string;
}

const FilmCardInfo = (props: FilmCardInfoProps) =>
{

    return (
        <>
            <div className="film-card-info-container">
                <div className="film-card-exit-button">
                </div>
                <div className="film-card-info-image">
                    <img src={props.image} />
                </div>
                <div className="film-card-info-details">
                    <h2>{props.film.title}</h2>
                    <p>{props.film.description}</p>
                </div>
            </div>
        </>
    )
}

export default FilmCardInfo;