import {Project} from './project'

export class Customer{
    constructor(
        public id: number,
        public name: string,
        public contact: string,
        public creationDate: Date,
        public projects: Project[]
    ){};
}