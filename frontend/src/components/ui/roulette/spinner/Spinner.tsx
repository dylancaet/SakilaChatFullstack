import {Film} from "../../../../types/api.ts";
import "./Spinner.css"
import FilmCard from "../../film/FilmCard.tsx";

interface SpinnerProps
{
    actualFilm: Film;
    fillerFilms: Film[];
}

const Spinner = (props: SpinnerProps) =>
{

    const filmCards = props.fillerFilms.map(f => <FilmCard key={f.id} film={f} />)

    return (
        <>
            <div className="spinner-container">
                <div className="spinner-track spinner-scroll">
                    {filmCards}
                </div>
            </div>
        </>
    )
}

export default Spinner;