const CreateFilm = () =>
{

    function handleSubit(formData: FormData)
    {
        alert("Submitted");
    }


    return (
        <>
            <form action={handleSubit} method='POST'>
                <label>
                    Title <input type='text' />
                </label>

                <label>
                    Description <input type='text' />
                </label>

                <label>
                    Price £ <input type='number' placeholder='0.00' step='0.5'/>
                </label>

                <button type="submit">Create</button>
            </form>
        </>
    )
}

export default CreateFilm;