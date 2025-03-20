import "./AdminPage.css"
import CreateFilmPanel from "../../components/ui/admin/film/CreateFilmPanel.tsx";
import ReadFilmPanel from "../../components/ui/admin/film/ReadFilmPanel.tsx";
import UpdateFilmPanel from "../../components/ui/admin/film/UpdateFilmPanel.tsx";
import DeleteFilmPanel from "../../components/ui/admin/film/DeleteFilmPanel.tsx";

const AdminPage = () =>
{
    return (
        <>
            <div className="admin-page-container">
                <div className="admin-page-table-container">
                    <ReadFilmPanel />
                </div>
                <div className="admin-page-panel-container">
                    <CreateFilmPanel />
                    <UpdateFilmPanel />
                    <DeleteFilmPanel />
                </div>
            </div>

            <div className="film-page-halftone"/>
            <div className="film-page-gradient"/>
        </>
    )
}

export default AdminPage;