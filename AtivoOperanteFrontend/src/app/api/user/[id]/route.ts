import { NextResponse } from 'next/server';

export async function GET(
  request: Request,
  context: { params: { id: string } }
) {
  try {
    const { id } = await Promise.resolve(context.params);
    const token = request.headers.get('cookie')?.split('; ')
      .find(row => row.startsWith('token='))?.split('=')[1];

    console.log('Fetching complaints for user:', id);
    console.log('Using token:', token);

    const response = await fetch(`http://localhost:8080/api/denuncia?userId=${id}`, {
      method: 'GET',
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json',
      }
    });

    console.log('Backend response status:', response.status);
    
    if (!response.ok) {
      const errorData = await response.text();
      console.error('Backend error response:', errorData);
      return NextResponse.json(
        { message: 'Get complaints failed', details: errorData },
        { status: response.status }
      );
    }

    // Handle 204 No Content response
    if (response.status === 204) {
      return NextResponse.json([], { status: 200 });
    }

    const data = await response.json();
    console.log('Backend success response type:', typeof data);
    console.log('Backend success response:', data);
    
    if (!Array.isArray(data)) {
      console.error('Backend response is not an array:', data);
      return NextResponse.json([], { status: 200 });
    }

    return NextResponse.json(data, { status: response.status });
  } catch (error) {
    console.error('User API (get complaints) error:', error);
    return NextResponse.json(
      { message: 'Internal server error', details: error instanceof Error ? error.message : 'Unknown error' },
      { status: 500 }
    );
  }
}