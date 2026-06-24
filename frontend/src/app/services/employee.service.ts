import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Employee {
  id?: number;
  firstName: string;
  middleName: string;
  paternalLastName: string;
  maternalLastName: string;
  age: number;
  gender: string;
  birthDate: string;
  jobTitle: string;
  createdAt?: string;
  active: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private apiUrl = 'http://localhost:8080/api/employees';

  constructor(private http: HttpClient) {}

  getAllEmployees(): Observable<Employee[]> {
    return this.http.get<Employee[]>(this.apiUrl);
  }

  getEmployeeById(id: number): Observable<Employee> {
    return this.http.get<Employee>(`${this.apiUrl}/${id}`);
  }

  searchByName(name: string): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${this.apiUrl}/search`, {
      params: { name }
    });
  }

  createEmployee(employee: Employee): Observable<Employee[]> {
    return this.http.post<Employee[]>(this.apiUrl, [employee]);
  }

  createMultipleEmployees(employees: Employee[]): Observable<Employee[]> {
    return this.http.post<Employee[]>(this.apiUrl, employees);
  }

  updateEmployee(id: number, employee: Employee): Observable<Employee> {
    return this.http.put<Employee>(`${this.apiUrl}/${id}`, employee);
  }

  deleteEmployee(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}

