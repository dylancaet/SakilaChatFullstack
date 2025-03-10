

export interface Store {
    id: number;
    address: Address;
}

export interface Address {
    id: number;
    address: string;
    districtName: string;
    postalCode?: string;    /* Technically would be an empty string, but never null */
    contactNumber?: string; /* Technically would be an empty string, but never null */
    city: City;
}

export interface City {
    id: number;
    cityName: string;
}

export interface Film {
    id: number;
    title: string;
    price: number;
    description: string
}

export interface PaginatedFilms
{
    currentPage: number;
    totalPages: number;
    totalFilms: number;
    films: Film[];
}