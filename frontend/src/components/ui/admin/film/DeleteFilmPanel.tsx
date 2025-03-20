import {useState} from "react";
import "./AdminPanel.css"

const DeleteFilmPanel = () =>
{
    const [showResponse, setShowResponse] = useState<boolean>(false);
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

        setShowResponse(true);
    }
    return (
        <div className="admin-panel-background">
            <h3>Delete</h3>
            <form action={handleSubmit} className="admin-panel-form">
                <label>
                    ID <input name="id" type="number" required />
                </label>

                <button type="submit">Delete</button>
            </form>
            {showResponse && (!response ? <i>Film NOT deleted!</i> : <i>Film deleted!</i>)}
        </div>
    )
}

export default DeleteFilmPanel;