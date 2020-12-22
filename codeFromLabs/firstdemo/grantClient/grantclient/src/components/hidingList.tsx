import React from "react";
import { Table } from "react-bootstrap";


export function HidingList({headers,records,show}:{headers:string[],records:string[][],show:boolean}) {
    if (!show ) {
        return null;
    }

    return (
        <div>
            <Table striped bordered hover>
                <thead>
                <tr>
                    {headers.map(
                        (header:string) => <th>{header}</th>
                    )}
                </tr>
                </thead>
                <tbody>
                {records.map(
                    (record:string[]) => <tr>{record.map(
                        (value:string) => <td>{value}</td>
                    )} </tr>
                )}
                </tbody>
            </Table>
        </div>

    );
}