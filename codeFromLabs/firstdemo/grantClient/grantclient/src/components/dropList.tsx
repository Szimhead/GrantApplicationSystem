import React from "react";
import { HidingList } from './hidingList';
import {Container} from "react-bootstrap";

interface DropListInterface {title:string, headers:string[], records: string[][], show:boolean}
//TODO: change list type from string to applicationList
export class DropList extends React.Component<DropListInterface, { showList:boolean }> {
    constructor(props: DropListInterface) {
        super(props);
        this.state = {showList: props.show}
        this.handleToggleClick = this.handleToggleClick.bind(this);
    }

    handleToggleClick() {
        this.setState(prevState => ({
            showList: !prevState.showList
        }));
    }

    render() {
        return (
            <div>
                <Container className="m-2">
                    <button className="w-100 border-0 text-left p-2" onClick={this.handleToggleClick}>
                        {this.props.title}
                    </button>
                    <HidingList records={this.props.records} show={this.state.showList} headers={this.props.headers}/>
                </Container>

            </div>
        );
    }
}