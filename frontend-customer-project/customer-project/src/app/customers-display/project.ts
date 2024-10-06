export class Project{
    constructor(
        public id: number,
        public name: string,
        public description: string, 
        public creationDate: Date
    ){}
};

export class ProjectForOutput{
    constructor(
        public name: string,
        public description: string, 
    ){}
}