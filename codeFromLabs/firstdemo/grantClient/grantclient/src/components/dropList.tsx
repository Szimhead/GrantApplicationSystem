import React from "react";

interface DropListInterface {title:string, list:string[],show:boolean}

function WarningBanner(props:{list:string[],show:boolean}) {
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
export class DropList extends React.Component<DropListInterface, { showWarning:boolean }> {
    constructor(props: DropListInterface) {
        super(props);
        this.state = {showWarning: props.show}
        this.handleToggleClick = this.handleToggleClick.bind(this);
    }

    handleToggleClick() {
        this.setState(prevState => ({
            showWarning: !prevState.showWarning
        }));
    }

    render() {
        return (
            <div>
                <button className="w-100"onClick={this.handleToggleClick}>
                    {this.props.title}
                </button>
                <WarningBanner show={this.state.showWarning}  list={this.props.list}/>
            </div>
        );
    }
}