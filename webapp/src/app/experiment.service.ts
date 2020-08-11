import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ExperimentService {

  constructor(private http: HttpClient) { }

  getExperiments(): Observable<object> {
    return this.http.get('http://127.0.0.1:8080/chaosblade/experiment');
  }

}
