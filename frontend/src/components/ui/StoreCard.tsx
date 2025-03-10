import {Store} from "../../types/api.ts";

interface StoreProps {
    store: Store
}

const StoreCard = (props: StoreProps) => {
    return (
        <div>
            <h4>{props.store.id}</h4>
            <p>{props.store.address.address}</p>
            <p>{props.store.address.districtName}</p>
        </div>
    )
}
export default StoreCard
