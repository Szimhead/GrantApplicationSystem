import React, {useState} from "react";
import { HidingList } from './hidingList';
import {GrantCallControllerApi} from "../clientAPI";

export type selectOneI = {
    recordsFirst: string[][],
    recordsSecond: string[][]
}

export const SelectOneButton  = ( {recordsFirst, recordsSecond}: selectOneI) => {

    const [showFirstList, setShowFirstList] = useState(true)
    const [allCalls, setAllCalls] = useState([] as string[][])
    const grantCallAPI = new GrantCallControllerApi();

    let handleToggleClick = () => {
        setShowFirstList( (showFirstList: boolean) => !showFirstList);
    }


    return (
        <div>
            <div className="row">
                <div className="col m-0 pr-0">
                    <button className="w-100 border-dark p-2" onClick={handleToggleClick}>
                        {"Open Grant Calls"}
                    </button>
                </div>
                <div className="col m-0 pl-0">
                    <button className="w-100 border-dark p-2" onClick={handleToggleClick}>
                        {"All Grant Calls"}
                    </button>
                </div>
            </div>

            <HidingList headers={["Grant Call name","Number of submitted applications"]}
                        records={recordsSecond} show={!showFirstList}/>

            <HidingList headers={["Grant Call name","Status","Opening date","Closing date"]}
                        records={recordsFirst} show={showFirstList}/>

        </div>
    );

}