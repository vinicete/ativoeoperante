'use client';

import { useAuth } from '@/contexts/AuthContext';
import Link from 'next/link';
import { useState } from 'react';
import { useEffect } from 'react';
import { Denuncia } from './nova-denuncia/page';


export default function CitizenDashboard() {
  const { user } = useAuth();
  const [denuncia, setDenuncia] = useState<Denuncia[]>([]);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchComplaints = async () => {
      if (user) {
        try {
          const response = await fetch(`/api/user/${user.id}`);
          if (!response.ok) {
            throw new Error('Failed to fetch complaints');
          }
          const data = await response.json();
          
          // Check if data is an array and has the expected structure
          if (Array.isArray(data)) {
            setDenuncia(data);
            setError(null);
          } else {
            console.error('Invalid data format:', data);
            setError('Invalid data format received');
            setDenuncia([]);
          }
        } catch (err) {
          console.error('Error fetching complaints:', err);
          setError('Failed to load complaints');
          setDenuncia([]);
        }
      }
    };

    fetchComplaints();
  }, [user]);

  return (
    <div className="space-y-6">
      <div className="sm:flex sm:items-center">
        <div className="sm:flex-auto">
          <h1 className="text-xl font-semibold text-gray-900">
            Minhas Denúncias
          </h1>
          <p className="mt-2 text-sm text-gray-700">
            Lista de todas as denúncias que você enviou.
          </p>
        </div>
        <div className="mt-4 sm:mt-0 sm:ml-16 sm:flex-none">
          <Link
            href="/cidadao/nova-denuncia"
            className="inline-flex items-center justify-center rounded-md border border-transparent bg-indigo-600 px-4 py-2 text-sm font-medium text-white shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 sm:w-auto"
          >
            Nova Denúncia
          </Link>
        </div>
      </div>

      <div className="mt-8 flex flex-col">
        <div className="-my-2 -mx-4 overflow-x-auto sm:-mx-6 lg:-mx-8">
          <div className="inline-block min-w-full py-2 align-middle md:px-6 lg:px-8">
            <div className="overflow-hidden shadow ring-1 ring-black ring-opacity-5 md:rounded-lg">
              {error ? (
                <div className="text-center py-12">
                  <p className="text-red-500">{error}</p>
                </div>
              ) : denuncia.length === 0 ? (
                <div className="text-center py-12">
                  <p className="text-gray-500">Você ainda não tem denúncias.</p>
                </div>
              ) : (
                <table className="min-w-full divide-y divide-gray-300">
                  <thead className="bg-gray-50">
                    <tr>
                      <th
                        scope="col"
                        className="py-3.5 pl-4 pr-3 text-left text-sm font-semibold text-gray-900 sm:pl-6"
                      >
                        Título
                      </th>
                      <th
                        scope="col"
                        className="px-3 py-3.5 text-left text-sm font-semibold text-gray-900"
                      >
                        Tipo
                      </th>
                      <th
                        scope="col"
                        className="px-3 py-3.5 text-left text-sm font-semibold text-gray-900"
                      >
                        Data
                      </th>
                      <th
                        scope="col"
                        className="px-3 py-3.5 text-left text-sm font-semibold text-gray-900"
                      >
                        Feedback
                      </th>
                    </tr>
                  </thead>
                  <tbody className="divide-y divide-gray-200 bg-white">
                    {denuncia.map((denuncia) => (
                      <tr key={denuncia.id}>
                        <td className="whitespace-nowrap py-4 pl-4 pr-3 text-sm font-medium text-gray-900 sm:pl-6">
                          {denuncia.titulo}
                        </td>
                        <td className="whitespace-nowrap px-3 py-4 text-sm text-gray-500">
                          {denuncia.tipo.nome}
                        </td>
                        <td className="whitespace-nowrap px-3 py-4 text-sm text-gray-500">
                          {new Date(denuncia.data).toLocaleDateString()}
                        </td>
                        <td className="whitespace-nowrap px-3 py-4 text-sm text-gray-500">
                          {denuncia.feedBack?.texto || '-'}
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              )}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
} 