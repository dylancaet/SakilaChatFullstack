const CreateFilm = () =>
{

    return (
        <>
            <form>
                <label>Title</label>
                <input type='text' />
                <label>Description</label>
                <input type='text' />
                <label>Price £</label>
                <input type='number' placeholder='0.00' step='0.5'/>
                <button type="submit">Create</button>
            </form>
        </>
    )
}

export default CreateFilm;