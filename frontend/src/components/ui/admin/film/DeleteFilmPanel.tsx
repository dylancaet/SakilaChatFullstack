import {useState} from "react";
import "./AdminPanel.css"

const DeleteFilmPanel = () =>
{
    const [response, setResponse] = useState<boolean>(false)

    async function handleSubmit(formData: FormData) {
        const id = formData.get("id");

        /* Attempt to delete film & handle response */
        fetch(import.meta.env.VITE_SAKILA_API + `/film/` + id, {
            method: "DELETE",
        }).then(async res => {
            if (res.status === 200) {
                setResponse(true);
            }
            else{
                setResponse(false)
            }
        })
    }
    return (
        <div className="admin-panel-base">
            <h3>Delete</h3>
            <form action={handleSubmit}>
                <label>
                    ID <input name="id" type="number" required />
                </label>

                <button type="submit">Delete</button>
            </form>
            {response === false ? <i>Film NOT deleted!</i> : <i>Film deleted!</i>}
        </div>
    )
}

export default DeleteFilmPanel;