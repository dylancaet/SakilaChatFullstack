import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import FilmPage from "./pages/film/FilmPage.tsx";
import "./global.css"

const root = document.getElementById("root");

createRoot(root!).render(
  <StrictMode>
      <FilmPage />
  </StrictMode>,
)
