import React from "react";
import { HidingList } from './hidingList';

interface selectOneButtonInterface {title:string[], headersFirst:string[], headersSecond:string[], recordsFirst: string[][], recordsSecond: string[][]}

export class SelectOneButton extends React.Component<selectOneButtonInterface, { showFirstList:boolean, showSecondList:boolean, }> {
    constructor(props: selectOneButtonInterface) {
        super(props);
        this.state = {showFirstList: true, showSecondList: false};
        this.handleToggleClick = this.handleToggleClick.bind(this);
    }

    handleToggleClick() {
        this.setState(prevState => ({
            showFirstList: !prevState.showFirstList,
            showSecondList: !prevState.showSecondList
        }));
    }

    render() {
        return (
            <div>
                <div className="row">
                    <div className="col m-0 pr-0">
                        <button className="w-100 border-dark" onClick={this.handleToggleClick}>
                            {this.props.title[0]}
                        </button>
                    </div>
                    <div className="col m-0 pl-0">
                        <button className="w-100 border-dark" onClick={this.handleToggleClick}>
                            {this.props.title[1]}
                        </button>
                    </div>
                </div>

                <HidingList headers={["Grant Call name","Number of submitted applications"]}
                            records={[["Grant call 1","50"],["Grant call 2","12"],
                                ["Grant call 3","3"],["Grant call 4","43"],]} show={this.state.showFirstList}/>

                <HidingList headers={["Grant Call name","Status","Opening date","Closing date"]}
                            records={[["Grant call 1","open","12.10.2020","30.12.2020"],["Grant call 2","closed","21.08.2020","30.10.2020"],
                                ["Grant call 3","open","01.12.2020","30.01.2021"],["Grant call 4","upcoming","15.01.2021","20.01.2021"],]}
                            show={this.state.showSecondList}/>

            </div>
        );
    }
}