export type GrantCall = {
    id: number,
    title: string,
    description: string,
    funding: number,
    openDate: Date,
    closeDate: Date,
    sponsorId: number
}

export type GrantCalls = { grantCalls: GrantCall[] }

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
