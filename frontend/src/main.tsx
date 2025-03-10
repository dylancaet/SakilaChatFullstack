import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { BrowserRouter, Routes, Route } from "react-router";
import FilmPage from "./pages/film/FilmPage.tsx";
import "./global.css"

const root = document.getElementById("root");

createRoot(root!).render(
  <StrictMode>
      <BrowserRouter>
          <Routes>
              <Route path="/" element={<FilmPage />} />
              <Route path="/films" element={<FilmPage />} />
          </Routes>
      </BrowserRouter>
  </StrictMode>,
)
