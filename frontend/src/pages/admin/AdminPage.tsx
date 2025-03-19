import "./AdminPage.css"
import CreateFilmPanel from "../../components/ui/admin/film/CreateFilmPanel.tsx";
import ReadFilmPanel from "../../components/ui/admin/film/ReadFilmPanel.tsx";
import UpdateFilmPanel from "../../components/ui/admin/film/UpdateFilmPanel.tsx";

const AdminPage = () =>
{
    return (
        <>
            <CreateFilmPanel />
            <ReadFilmPanel />
            <UpdateFilmPanel />
        </>
    )
}

export default AdminPage;