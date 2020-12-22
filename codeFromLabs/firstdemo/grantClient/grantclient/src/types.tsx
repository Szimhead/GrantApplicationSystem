import {GrantCallDTO} from "./clientAPI";

export type GrantCalls = { grantCalls: GrantCallDTO[] }

export type Student = {
    id: number,
    name: string,
    email: string,
    address: string,
}

export type Answer = {
    requirement: Requirement,
    content: string
}

export type Review = {
    id: number,
    isAccepted: boolean,
    comment: string,
    reviewerId: number
}

export type Application = {
    id: number
}

export type Panel = {
    id: number
}

export type Requirement = {
    name: string,
    contentType: string
}
