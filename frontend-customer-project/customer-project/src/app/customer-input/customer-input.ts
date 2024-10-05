import { ProjectForInput } from "./project-input";

export class CustomerForInput{
    constructor(
        public name: string,
        public contact: string,
        public project: ProjectForInput
    ){};
}