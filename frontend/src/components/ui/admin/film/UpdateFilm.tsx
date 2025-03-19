import {useState} from "react";
import {Film} from "../../../../types/api.ts";


const UpdateFilm = () =>
{
    const [response, setResponse] = useState<Film | undefined>()

    async function handleSubmit(formData: FormData)
    {
        const dict = new Map<string, string>();

        for (const formDatum of formData)
        {
            if (formDatum[1].toString().length > 0)
                dict.set(formDatum[0], formDatum[1].toString());
        }

        if (dict.get("id") == null)
            return;

        /* Attempt to create film & handle response */
        await fetch(import.meta.env.VITE_SAKILA_API + `/film`, {
            method: "PATCH",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(Object.fromEntries(dict))
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
                    ID <input name="id" type="number" required />
                </label>

                <label>
                    Title <input name="title" type="text" />
                </label>

                <label>
                    Description <input name="description" type="text" />
                </label>

                <label>
                    Price £ <input name="price" type="number" placeholder="0.00" step="0.01" />
                </label>

                <button type="submit">Update</button>
            </form>
            {response === undefined ? <i>Film NOT updated!</i> : <i>Film updated!</i>}
        </div>
    )
}

export default UpdateFilm;