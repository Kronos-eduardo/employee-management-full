import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { EmployeeService, Employee } from '../../services/employee.service';

@Component({
  selector: 'app-employee-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './employee-form.component.html',
  styleUrls: ['./employee-form.component.css']
})
export class EmployeeFormComponent implements OnInit {
  employee: Employee = {
    firstName: '',
    middleName: '',
    paternalLastName: '',
    maternalLastName: '',
    age: 18,
    gender: 'M',
    birthDate: '',
    jobTitle: '',
    active: true
  };
  loading: boolean = false;
  error: string = '';
  isEditMode: boolean = false;
  employeeId: number | null = null;

  constructor(
    private employeeService: EmployeeService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const id = Number(params['id']);
      if (!isNaN(id)) {
        this.isEditMode = true;
        this.employeeId = id;
        this.loadEmployee(id);
      }
    });
  }

  loadEmployee(id: number): void {
    this.loading = true;
    this.employeeService.getEmployeeById(id).subscribe({
      next: (data) => {
        this.employee = data;
        this.loading = false;
      },
      error: (error) => {
        this.error = 'Error loading employee';
        this.loading = false;
      }
    });
  }

  saveEmployee(): void {
    this.loading = true;
    this.error = '';

    const employeeToSend = {
      ...this.employee,
      gender: this.employee.gender === 'Female' ? 'F' : 'M',
      birthDate: this.convertDateFormat(this.employee.birthDate) 
    };

    if (this.isEditMode && this.employeeId) {
      // Update mode
      this.employeeService.updateEmployee(this.employeeId, employeeToSend).subscribe({
        next: () => {
          this.router.navigate(['/employees']);
        },
        error: (error: any) => {
          this.handleError(error);
        }
      });
    } else {
      // Create mode
      this.employeeService.createEmployee(employeeToSend).subscribe({
        next: () => {
          this.router.navigate(['/employees']);
        },
        error: (error: any) => {
          this.handleError(error);
        }
      });
    }
  }

  private handleError(error: any): void {
    // Capturar el error detallado del servidor
    if (error.error && typeof error.error === 'object' && error.error.message) {
      this.error = `Error saving employee: ${error.error.message}`;
    } else if (error.message) {
      this.error = `Error saving employee: ${error.message}`;
    } else {
      this.error = `Error saving employee (HTTP ${error.status})`;
    }
    this.loading = false;
  }

  private convertDateFormat(dateStr: string): string {
    if (!dateStr || dateStr.trim() === '') {
      return '';
    }
    const parts = dateStr.split('-');
    if (parts.length === 3) {
      return `${parts[2]}-${parts[1]}-${parts[0]}`;
    }
    return dateStr;
  }

  cancel(): void {
    this.router.navigate(['/employees']);
  }
}

