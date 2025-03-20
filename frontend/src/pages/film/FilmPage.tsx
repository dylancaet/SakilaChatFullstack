import FilmCollection from "../../components/ui/film/FilmCollection.tsx";
import ChatBox from "../../components/ui/chatbox/ChatBox.tsx";
import "./FilmPage.css"

const FilmPage = () =>
{
    return (
        <>
            <title>Sakila Film Chat</title>

            <div className="film-page-container">
                <div className="film-page-chatbox-container">
                    <ChatBox/>
                </div>

                <div className="film-page-collection-container">

                    <div className="film-page-sakila-logo-container">
                        <h1 className="film-page-sakila-logo">SakilaChat</h1>
                        <h5>A place to chat and discuss sakila films</h5>
                    </div>

                    <div className="film-page-collection-results-container">
                        <FilmCollection/>
                    </div>
                </div>
            </div>

            <div className="film-page-halftone"/>
            <div className="film-page-gradient"/>
        </>
    )
}

export default FilmPage;