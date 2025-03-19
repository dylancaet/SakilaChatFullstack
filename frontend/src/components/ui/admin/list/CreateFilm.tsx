import {useEffect, useState} from "react";
import {Film} from "../../../../types/api.ts";


const CreateFilm = () =>
{

    function handleSubmit(formData: FormData)
    {
        const title = formData.get('title');
        const description = formData.get('description');
        const price = formData.get('price');


        fetch(import.meta.env.VITE_SAKILA_API+`/film`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({title, description, price})
        })
    }

    return (
        <>
            <form action={handleSubmit} method="POST">
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
        </>
    )
}

export default CreateFilm;