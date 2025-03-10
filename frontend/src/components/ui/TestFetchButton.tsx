import {JSX, useEffect, useState} from "react";
import StoreCard from "./StoreCard.tsx";
import {Store} from "../../types/api.ts";

export default function TestFetchButton(): JSX.Element {
    const [stores, setStores] = useState<Store[]>([]);

    useEffect(() =>
    {
        fetch(`http://localhost:8080/api/sakila/store/list`)
            .then(res => res.json())
            .then(d => setStores(d));
    }, [])

    /*  Rendering lists & keys
        By default React uses the array index as a key
        Keys allow React to identify the item throughout its lifetime

        https://react.dev/learn/rendering-lists#keeping-list-items-in-order-with-key
    */
    const storeItems = stores.map(store => <StoreCard key={store.id} store={store}/>)

    return (
        <>
            {storeItems}
        </>
    );
}