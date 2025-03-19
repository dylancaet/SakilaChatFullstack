import "./AdminPage.css"
import CreateFilm from "../../components/ui/admin/film/CreateFilm.tsx";
import ReadFilm from "../../components/ui/admin/film/ReadFilm.tsx";
import UpdateFilm from "../../components/ui/admin/film/UpdateFilm.tsx";

const AdminPage = () =>
{
    return (
        <>
            <CreateFilm />
            <ReadFilm />
            <UpdateFilm />
        </>
    )
}

export default AdminPage;