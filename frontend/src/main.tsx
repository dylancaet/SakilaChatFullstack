import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import "./global.css"
import AdminPage from "./pages/admin/AdminPage.tsx";
import FilmPage from "./pages/film/FilmPage.tsx";
import {BrowserRouter, Route, Routes} from "react-router";

const root = document.getElementById("root");

createRoot(root!).render(
  <StrictMode>
      <BrowserRouter>
          <Routes>
              <Route path="/" element={<FilmPage />} />
              <Route path="/admin" element={<AdminPage />} />
          </Routes>
      </BrowserRouter>
  </StrictMode>,
)
