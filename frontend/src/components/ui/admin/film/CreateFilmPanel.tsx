import {useState} from "react";
import {Film} from "../../../../types/api.ts";


const CreateFilmPanel = () =>
{
    const [response, setResponse] = useState<Film | undefined>()

    async function handleSubmit(formData: FormData) {
        const title = formData.get('title');
        const description = formData.get('description');
        const price = formData.get('price');

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
    }

    return (
        <div>
            <form action={handleSubmit}>
                <label>
                    Title <input name="title" type="text" required />
                </label>

                <label>
                    Description <input name="description" type="text" required />
                </label>

                <label>
                    Price £ <input name="price" type="number" placeholder="0.00" step="0.01" required/>
                </label>

                <button type="submit">Create</button>
            </form>
            {response === undefined ? <i>Film NOT created!</i> : <i>Film created!</i>}
        </div>
    )
}

export default CreateFilmPanel;