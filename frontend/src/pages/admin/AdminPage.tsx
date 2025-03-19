import "./AdminPage.css"
import CreateFilmPanel from "../../components/ui/admin/film/CreateFilmPanel.tsx";
import ReadFilmPanel from "../../components/ui/admin/film/ReadFilmPanel.tsx";
import UpdateFilmPanel from "../../components/ui/admin/film/UpdateFilmPanel.tsx";
import DeleteFilmPanel from "../../components/ui/admin/film/DeleteFilmPanel.tsx";

const AdminPage = () =>
{
    return (
        <>
            <CreateFilmPanel />
            <ReadFilmPanel />
            <UpdateFilmPanel />
            <DeleteFilmPanel />
        </>
    )
}

export default AdminPage;