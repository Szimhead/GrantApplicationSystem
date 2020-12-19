import React from "react";
import { HidingList } from './hidingList';

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
                <button className="w-100 border-0" onClick={this.handleToggleClick}>
                    {this.props.title}
                </button>
                <HidingList records={this.props.records} show={this.state.showList} headers={this.props.headers}/>
            </div>
        );
    }
}