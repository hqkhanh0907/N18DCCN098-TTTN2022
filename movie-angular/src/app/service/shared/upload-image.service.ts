import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ImageService {
  public headers: any | null = 'Bearer ' + sessionStorage.getItem('token');
  private httpOptions = {
    headers: new HttpHeaders({
      Authorization: this.headers,
    }).set('Content-Type', 'multipart/form-data'),
  };

  constructor(private httpClient: HttpClient) {}

  uploadImageAcc(uploadImage: File, accId: number): Observable<any> {
    this.headers = sessionStorage.getItem('token');
    this.httpOptions = {
      headers: new HttpHeaders({
        Authorization: this.headers,
      }).set('Content-Type', 'multipart/form-data'),
    };
    const uploadImageData = new FormData();
    uploadImageData.append('image', uploadImage, uploadImage.name);
    return this.httpClient.put(
      'http://localhost:8080/api/acc/saveImageAccount/' + accId,
      uploadImageData
    );
  }

  uploadImageAll(uploadImage: File, type: string): Observable<any> {
    this.headers = sessionStorage.getItem('token');
    this.httpOptions = {
      headers: new HttpHeaders({
        Authorization: this.headers,
      }).set('Content-Type', 'multipart/form-data'),
    };
    const uploadImageData = new FormData();
    uploadImageData.append('image', uploadImage, uploadImage.name);
    return this.httpClient.post(
      'http://localhost:8080/upload-image/' + uploadImage.name + '/' + type,
      uploadImageData
    );
  }
  uploadEditImageMovie(
    uploadImage: File,
    type: string,
    idMovie: number
  ): Observable<any> {
    this.headers = sessionStorage.getItem('token');
    this.httpOptions = {
      headers: new HttpHeaders({
        Authorization: this.headers,
      }).set('Content-Type', 'multipart/form-data'),
    };
    const uploadImageData = new FormData();
    uploadImageData.append('image', uploadImage, uploadImage.name);
    return this.httpClient.post(
      'http://localhost:8080/uploadimage/' + idMovie + '/' + type,
      uploadImageData
    );
  }
  public getAccImage(url: string): Observable<any> {
    this.httpOptions = {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
    };
    const path = new FormData();
    path.set('url', url);
    return this.httpClient.post('http://localhost:8080/getImage/', path);
  }
}
