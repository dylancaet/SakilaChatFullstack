const CreateFilm = () =>
{

    function handleSubit(formData: FormData)
    {
        const title = formData.get('title');
        const description = formData.get('description');
        const price = formData.get('price');

        alert(`Submitted\n ${title} \n ${description} \n ${price}`);
    }

    return (
        <>
            <form action={handleSubit} method='POST'>
                <label>
                    Title <input name="title" type='text' required />
                </label>

                <label>
                    Description <input name="description" type='text' required />
                </label>

                <label>
                    Price £ <input name="price" type='number' placeholder='0.00' step='0.5' min='0.00' required/>
                </label>

                <button type="submit">Create</button>
            </form>
        </>
    )
}

export default CreateFilm;