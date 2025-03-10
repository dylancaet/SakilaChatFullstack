import FilmCollection from "../../components/ui/film/FilmCollection.tsx";
import ChatBox from "../../components/ui/chatbox/ChatBox.tsx";
import "./FilmPage.css"
import {useState} from "react";

const FilmPage = () =>
{
    const [chatOpen, setChatOpen] = useState(false);


    const chatboxToggle = () => {setChatOpen(!chatOpen);};

    return (
        <>
            <div className={`film-chatbox-container ${chatOpen ? "film-chatbox-open" : "film-chatbox-close"}`}>
                <div className={`film-chatbox-chatbox ${chatOpen ? "" : "hidden"}`}>
                    <ChatBox/>
                </div>
                <div className="film-chatbox-title" onClick={chatboxToggle}>
                    <img src="./icons/icon_user.gif"/>
                    <h4>Chat</h4>
                </div>
            </div>

            <div className="film-main-content-container">
                <div className="film-page-header">
                    <div className="film-page-header-content">
                        <h1>Sakila Film Chat</h1>
                        <h4>discuss films and prices</h4>
                    </div>
                </div>
                <FilmCollection/>
            </div>
        </>
    )
}

export default FilmPage;