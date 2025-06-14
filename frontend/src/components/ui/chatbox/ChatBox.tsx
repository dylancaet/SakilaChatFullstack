import {useEffect, useState} from "react";
import useWebSocket, {ReadyState} from "react-use-websocket";
import "./ChatBox.css"

export default function ChatBox()
{
    const [userMessage, setUserMessage] = useState("");
    const [messageHistory, setMessageHistory] = useState<string[]>([])

    const WS_URL = import.meta.env.VITE_WS_URL+"/ws/message";
    const { readyState, sendMessage, lastMessage } = useWebSocket(
        WS_URL,
        {
            share: false,
            shouldReconnect: ()=> true,
            onOpen: ()=> console.log("Connection opened"),
            onClose: ()=> console.log("Connection closed")
        },
    )

    /* Connection changed */
    useEffect(() => {
        console.log(`Connection state: ${ReadyState[readyState]}`)
    }, [readyState])


    /* Message History */
    useEffect(() => {
        setMessageHistory([...messageHistory, lastMessage?.data])
        console.log(`Got a new message: ${lastMessage?.data}`)
    }, [lastMessage])

    const messages = messageHistory.map(msg => <p>{msg}</p>)

    const handleUserSendMessage = (e: React.FormEvent<HTMLFormElement>) =>
    {
        console.log("attempted to send message");
        e.preventDefault();

        if (userMessage.length < 1)
            return;

        sendMessage(userMessage);
        setUserMessage("");
    }

    return (
        <div className="chatbox">
            <form onSubmit={(e) => handleUserSendMessage(e)}>
                <input type="text" value={userMessage} onChange={e => setUserMessage(e.target.value)}/>
            </form>
            <div className="message-container">
                <div className="message-container-2">
                    {messages}
                </div>
            </div>
        </div>
    );
}