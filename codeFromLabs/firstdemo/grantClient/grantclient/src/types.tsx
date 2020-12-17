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