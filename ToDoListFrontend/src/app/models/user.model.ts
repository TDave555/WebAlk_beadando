import { Category } from "./category.model";
import { Todo } from "./todo.model";

export class User {
  id: number=0;
  username: string="";
  role: string="user";
  todos?: Todo[];
categories?: Category[] ;}
