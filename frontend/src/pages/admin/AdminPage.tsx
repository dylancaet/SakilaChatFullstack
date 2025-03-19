import "./AdminPage.css"
import CreateFilm from "../../components/ui/admin/film/CreateFilm.tsx";
import ReadFilm from "../../components/ui/admin/film/ReadFilm.tsx";

const AdminPage = () =>
{
    return (
        <>
            <CreateFilm />
            <ReadFilm />
        </>
    )
}

export default AdminPage;