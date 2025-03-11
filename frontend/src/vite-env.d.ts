/// <reference types="vite/client" />

interface ImportMetaEnv
{
    readonly VITE_SAKILA_API: string
    readonly VITE_WS_URL: string
}

interface ImportMeta {
    readonly env: ImportMetaEnv
}