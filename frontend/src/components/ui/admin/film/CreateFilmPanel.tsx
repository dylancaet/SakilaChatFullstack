import {useState} from "react";
import {Film} from "../../../../types/api.ts";
import FilmCard from "../../film/FilmCard.tsx";
import "./AdminPanel.css";


const CreateFilmPanel = () =>
{
    const [showResponse, setShowResponse] = useState<boolean>(false);
    const [response, setResponse] = useState<Film | undefined>()

    async function handleSubmit(formData: FormData) {
        const title = formData.get('title');
        const description = formData.get('description');
        const price = Number(formData.get('price')).toFixed(2);

        /* Attempt to create film & handle response */
        fetch(import.meta.env.VITE_SAKILA_API + `/film`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({title, description, price})
        }).then(async res => {
            if (res.status === 200) {
                const resFilm: Film = await res.json();
                setResponse(resFilm);
            }
            else{
                setResponse(undefined)
            }
        })

        setShowResponse(true);
    }

    return (
        <div className="admin-panel-background">
            <h3>Create</h3>
            <form action={handleSubmit} className="admin-panel-form">
                <label>
                    Title <input name="title" type="text" required />
                </label>

                <label>
                    Description <input name="description" type="text" required />
                </label>

                <label>
                    Price £ <input name="price" type="number" placeholder="0.00" step="0.01" min="0.00" required/>
                </label>

                <button type="submit">Create</button>
            </form>
            {response && <FilmCard film={response} clickable={true} />}
            {showResponse && (response === undefined && showResponse ? <i>Film NOT created!</i> : <i>Film created!</i>)}
        </div>
    )
}

export default CreateFilmPanel;