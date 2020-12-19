import React from "react";

interface DropListInterface {title:string, list:string[],show:boolean}

function List(props:{list:string[],show:boolean}) {
    if (!props.show) {
        return null;
    }

    return (
        <div>
            <ul>
                {props.list.map(
                    (grantCall:string) => <p>{grantCall}</p>
                )}
            </ul>
        </div>
    );
}
//TODO: change list type from string to applicationList
export class DropList extends React.Component<DropListInterface, { show:boolean }> {
    constructor(props: DropListInterface) {
        super(props);
        this.state = {show: props.show}
        this.handleToggleClick = this.handleToggleClick.bind(this);
    }

    handleToggleClick() {
        this.setState(prevState => ({
            show: !prevState.show
        }));
    }

    render() {
        return (
            <div>
                <button className="w-100"onClick={this.handleToggleClick}>
                    {this.props.title}
                </button>
                <List show={this.state.show}  list={this.props.list}/>
            </div>
        );
    }
}